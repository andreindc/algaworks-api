package com.algaworksapi.algaworksapi.repository.lancamento;

import com.algaworksapi.algaworksapi.model.Categoria_;
import com.algaworksapi.algaworksapi.model.Lancamento;
import com.algaworksapi.algaworksapi.model.Lancamento_;
import com.algaworksapi.algaworksapi.model.Pessoa_;
import com.algaworksapi.algaworksapi.repository.fiter.LancamentoFilter;
import com.algaworksapi.algaworksapi.repository.projectio.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable; //Pendiente
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar (LancamentoFilter lancamentoFilter, Pageable pageable){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criterio = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criterio.from(Lancamento.class);

        //Criar as restriçoes
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criterio.where(predicates);
        TypedQuery<Lancamento> query = manager.createQuery(criterio);
        adicionarRestricoesDePaginacao(query,pageable);
        return new PageImpl(query.getResultList(), pageable, total(lancamentoFilter));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(ResumoLancamento.class
                , root.get("codigo"), root.get("decricao")
                , root.get("dataVencimento"), root.get("dataPagamento")
                , root.get("valor"), root.get("tipo")
                , root.get("categoria").get("nome")
                , root.get("pessoa").get("nome")));

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }


    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criterio = builder.createQuery(Long.class);
        Root<Lancamento> root = criterio.from(Lancamento.class);
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criterio.where(predicates);
        criterio.select(builder.count(root));
        return manager.createQuery(criterio).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable ) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
         List<Predicate> predicates = new ArrayList<>();
         System.out.println(lancamentoFilter.getDecricao());
        if(lancamentoFilter.getDecricao() !=null){
           predicates.add(builder.like(
               builder.lower(root.get("decricao")), "%"+ lancamentoFilter.getDecricao().toLowerCase()+ "%"));

        }
        if(lancamentoFilter.getDataVencimento() !=null){ //Mayores a esta data
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimento()));
        }
        if(lancamentoFilter.getDataVencimentoAte() !=null){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()));

        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }



}

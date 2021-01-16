package br.com.gok.templateapi.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.gok.templateapi.model.Planet;

@Repository
public class PlanetRepositoryImpl implements PlanetRepositoryCustom {

    private EntityManager em;

    public PlanetRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Planet> filterByName(String text) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Planet> criteria = builder.createQuery(Planet.class);
		Root<Planet> root = criteria.from(Planet.class);
		List<Predicate> predicates = new LinkedList<>();
		predicates.add(builder.like(root.<String>get("name"), "%"+text+"%"));
		criteria.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<Planet> filterByPopulation(Long min, Long max) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Planet> criteria = builder.createQuery(Planet.class);
		Root<Planet> root = criteria.from(Planet.class);
		List<Predicate> predicates = new LinkedList<>();
		predicates.add(builder.greaterThanOrEqualTo(root.<Long>get("population"), min)); 
		predicates.add(builder.lessThanOrEqualTo(root.<Long>get("population"), max));
		criteria.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return em.createQuery(criteria).getResultList();
    }
    
}

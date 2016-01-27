package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.spi.provider.hibernate.HibernateCriteriaPersistenceProvider;
import br.com.thiaguten.richspring.model.Todo;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("todoDAO")
public class TodoDAOImpl extends GenericBaseDAO<Todo, Long> implements TodoDAO {

    @Autowired
    private HibernateCriteriaPersistenceProvider persistenceProvider;

    @Override
    public HibernateCriteriaPersistenceProvider getPersistenceProvider() {
        return this.persistenceProvider;
    }

    @Override
    public List<Todo> find(Todo todo) {
        if (todo != null) {
            List<Criterion> criterions = new ArrayList<>();
            if (StringUtils.hasText(todo.getResume())) {
                criterions.add(Restrictions.ilike("resume", todo.getResume(), MatchMode.ANYWHERE));
            }
            if (StringUtils.hasText(todo.getDescription())) {
                criterions.add(Restrictions.ilike("description", todo.getDescription(), MatchMode.ANYWHERE));
            }
            return persistenceProvider.findByCriteria(getEntityClass(), criterions);
        }
        return Collections.emptyList();
    }
}

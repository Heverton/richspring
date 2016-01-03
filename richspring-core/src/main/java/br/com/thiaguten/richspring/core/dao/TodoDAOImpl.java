package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.persistence.dao.GenericBaseDAO;
import br.com.thiaguten.persistence.provider.PersistenceProvider;
import br.com.thiaguten.persistence.provider.hibernate.HibernatePersistenceProvider;
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
    private PersistenceProvider persistenceProvider;

    @Override
    public PersistenceProvider getPersistenceProvider() {
        return this.persistenceProvider;
    }

    public void setPersistenceProvider(PersistenceProvider persistenceProvider) {
        this.persistenceProvider = persistenceProvider;
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
            return ((HibernatePersistenceProvider) persistenceProvider).findByCriteria(getEntityClass(), criterions);
        }
        return Collections.emptyList();
    }
}

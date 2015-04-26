package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.richspring.model.Todo;
import br.com.thiaguten.richspring.persistence.dao.AbstractDAO;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("todoDAO")
public class TodoDAOImpl extends AbstractDAO<Todo, Long> implements TodoDAO {

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
            return findByCriteria(criterions);
        }
        return Collections.emptyList();
    }

}

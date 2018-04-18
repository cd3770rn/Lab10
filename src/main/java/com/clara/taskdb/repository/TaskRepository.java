package com.clara.taskdb.repository;

import com.clara.taskdb.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByOrderByUrgentDesc();

    /* Custom query for updating a Task objet, setting the completed value
     * Returns the number of rows modified,
     * So if the Task is found and updated, returns 1
     * If the Task with this id is not in the DB, no rows are update,d so returns 0 */
    @Transactional
    @Modifying
    @Query("Update Task t set t.completed = ?1 where t.id = ?2")
    int setTaskCompleted(boolean completed, long id);
}

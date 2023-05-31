
/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 21-05-2023      1.0                 DucTM           First Implement
 * 26-05-2023      1.0                 DucTM           Add search() method
 * 31-05-2023      1.0                 DucTM           Add getBy(int agentid) method
 */
package dao;

import java.util.List;

/*
 * This interface contains basic methods for performing CRUD actions from/to the database
 * All DAO classess will implement this interface
 * 
 * 
 * @author DucTM 
 * @param <T> class of the object the implementing DAO will handle
 */
public interface DAO<T> {

    List<T> getAll();

    List<T> get(int id);

    void save(T t);

    void update(T t);

    void delete(T t);
    
    List<T> search(String keyword);
}

package pe.edu.utp.CorefoSpringServer.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.CorefoSpringServer.models.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {
}

package File.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import File.Entity.File;

public interface FileRepository extends JpaRepository<File,Long>
{

}

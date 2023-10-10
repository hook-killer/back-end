package HookKiller.server.common.repository;


import HookKiller.server.common.entity.FileResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileResources, Long> {
}

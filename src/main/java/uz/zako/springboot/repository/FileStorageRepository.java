package uz.zako.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.springboot.domain.FileStorage;
import uz.zako.springboot.domain.FileStorageStatus;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    public FileStorage findByHashId(String hashId);
    public List<FileStorage> findAllByFileStorageStatus(FileStorageStatus status);
}

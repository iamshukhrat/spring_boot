package uz.zako.springboot.service;


import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.springboot.domain.FileStorage;
import uz.zako.springboot.domain.FileStorageStatus;
import uz.zako.springboot.repository.FileStorageRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    @Value("${upload.folder}")
    private String uploadFolder;
    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids();
    }

    public void save(MultipartFile multipartFile){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorageRepository.save(fileStorage);
        Date date = new Date();
        File uploadFile = new File(String.format("%s/upload_files/%d/%d/%d/", uploadFolder
                , 1900 + date.getYear()
                , 1 + date.getMonth()
                , date.getDate()));
        if (!uploadFile.exists() && uploadFile.mkdirs()){
//            System.out.println("Ushbu papkalar muvaffaqiyatli yaratildi!");
        }
            fileStorage.setHashId(hashids.encode(fileStorage.getId()));
            fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s"
                    , 1900 + date.getYear()
                    , 1 + date.getMonth()
                    , date.getDate()
                    ,fileStorage.getHashId()
                    ,fileStorage.getExtension()));
            fileStorageRepository.save(fileStorage);
        uploadFile = uploadFile.getAbsoluteFile();
        File file = new File(uploadFile
                , String.format("/%s.%s",fileStorage.getHashId()
                , fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getExt(String name){
        String ext = null;
        if (name!=null && !name.isEmpty()){
            int ind = name.lastIndexOf('.');
            if (ind > 0 && ind <= name.length() - 2){
                ext = name.substring(ind+1);
            }
        }
        return ext;
    }
    @Transactional(readOnly = true)
    public FileStorage findByHashId(String HashId){
        return fileStorageRepository.findByHashId(HashId);
    }

    public void delete(String HashId){
        FileStorage fileStorage = findByHashId(HashId);
        File file = new File(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath()));
        if (file.delete()){
            fileStorageRepository.delete(fileStorage);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteByStatus(){
        List<FileStorage> list = fileStorageRepository.findAllByFileStorageStatus(FileStorageStatus.DRAFT);
        if (list!=null){
            list.forEach(fileStorage -> {
                File file = new File(String.format("%s/%s",uploadFolder, fileStorage.getUploadPath()));
                if (file.delete()) {
                    fileStorageRepository.delete(fileStorage);
                }
            });
        }
    }
}

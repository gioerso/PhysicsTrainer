package src.server.jointx.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import src.server.jointx.entities.Image;
import src.server.jointx.enums.Extension;
import src.server.jointx.repos.ImageRepo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepo imageRepo;

    public byte[] getImage(Long id) {
        Optional<Image> optionalImage = imageRepo.findById(id);
        if (optionalImage.isEmpty()) return new byte[0];
        Image presentImage = optionalImage.get();
        File imageFile = new File(presentImage.getAbsolutePath());
        BufferedImage image;
        try {
            image = ImageIO.read(imageFile);
        } catch (Exception e) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, presentImage.getExtension().getCode(), byteArrayOutputStream);
        } catch (Exception e) {
            return new byte[0];
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Scheduled(cron = "0 0-23 ? * MON-FRI")
    private void compareDirFilesToDatabaseTuples() {
        File questionDirectory = new File("classpath:/resources/question_images");
        File theoryDirectory = new File("classpath:/resources/theory_images");
        new Thread(() -> iterateDirectory(questionDirectory, false)).start();
        new Thread(()-> iterateDirectory(theoryDirectory, true)).start();
    }

    private void iterateDirectory(File directory, boolean theoretical) {
        if (!directory.isDirectory()) return;
        File[] directoryFiles = directory.listFiles();
        if (directoryFiles == null) return;
        for (File file : directoryFiles) {
            String name = file.getName().split("\\.")[0];
            String extension = file.getName().split("\\.")[1];
            try {
                if (imageRepo.findAllByTheoreticalIs(theoretical).stream().anyMatch(x
                        -> !x.isTheoretical()
                        && x.getName().equals(name)
                        && x.getExtension().equals(Extension.fromCode(extension)
                ))) continue;
            } catch (Exception e) {
                continue;
            }
            Image newImage = new Image(name, Extension.fromCode(extension), theoretical);
            imageRepo.save(newImage);
        }
    }
}

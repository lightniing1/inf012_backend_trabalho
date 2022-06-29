package br.ifba.pweb.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    public ResponseEntity salvaArquivo(String uid, MultipartFile multipartFile, String nomeArquivo) throws IOException {

        Path caminhoDiretorio = Paths.get("/fotos/"); //Windows
        if (!Files.isDirectory(caminhoDiretorio)) {
            Files.createDirectories(caminhoDiretorio);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path caminhoArquivo = caminhoDiretorio.resolve(nomeArquivo);
            Files.copy(inputStream, caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo criado em: " + caminhoArquivo.toAbsolutePath());
;        } catch (IOException ioe) {
            //throw new IOException ("Erro ao salvar imagem: " , ioe);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

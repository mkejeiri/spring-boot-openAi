package edu.ai.rag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class VectorStoreConfig {

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) throws IOException {
        SimpleVectorStore store = SimpleVectorStore.builder(embeddingModel).build();

        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

        if (vectorStoreFile.exists()) {
            log.debug("vector store found ;: {}. start loading the vector store file....", vectorStoreProperties.getVectorStorePath());
            store.load(vectorStoreFile);
            log.debug("Loading done.");
        } else {
            log.debug("Loading documents into vector store");

            ClassLoader cl = this.getClass().getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            var documents = resolver.getResources("classpath:trimmeddata/*");
            Arrays.stream(documents).forEach(document -> {
                log.debug("Loading document: " + document.getFilename());
                TikaDocumentReader documentReader = null;
                try {
                    documentReader = new TikaDocumentReader(String.valueOf(document.getURI()));
                    List<Document> docs = documentReader.get();
                    TextSplitter textSplitter = new TokenTextSplitter();
                    List<Document> splitDocs = textSplitter.apply(docs);
                    store.add(splitDocs);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            store.save(vectorStoreFile);
        }
        return store;
    }
}
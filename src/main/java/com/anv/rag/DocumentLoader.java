package com.anv.rag;

import com.pgvector.PGvector;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentLoader {
    private final VectorStore vectorStore;


    public void loadDocuments() {

        PagePdfDocumentReader reader =
                new PagePdfDocumentReader(
                        new ClassPathResource("documents/syllabus.pdf"));

        List<Document> documents = reader.get();

//        TokenTextSplitter splitter = new TokenTextSplitter();
//
//        List<Document> chunks = splitter.apply(documents);
//
//        vectorStore.add(chunks);
        vectorStore.add(documents);

        System.out.println("Documents Loaded : " + documents.size());
    }
}

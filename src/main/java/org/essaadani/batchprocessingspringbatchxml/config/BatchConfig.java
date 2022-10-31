package org.essaadani.batchprocessingspringbatchxml.config;

import org.essaadani.batchprocessingspringbatchxml.Constantes.ImportUsersConst;
import org.essaadani.batchprocessingspringbatchxml.dto.UserDTO;
import org.essaadani.batchprocessingspringbatchxml.entities.User;
import org.essaadani.batchprocessingspringbatchxml.processor.UserItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;

import static org.essaadani.batchprocessingspringbatchxml.Constantes.ImportUsersConst.QUERY_INSERT_USER;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemWriter<User> xmlToDatabaseUserWriter;

    @Autowired
    private ItemReader<UserDTO> xmlToDatabaseUserReader;

    @Autowired
    private ItemProcessor<UserDTO, User> xmlToDatabaseProcessor;


    @Bean
    public Job importUsersJob(@Qualifier("xmlFileToDatabaseStep") Step xmlUserStep){
        return jobBuilderFactory
                .get(ImportUsersConst.JOB_NAME)
//                .start(xmlUserStep)
//                .build();
                .incrementer(new RunIdIncrementer())
                .flow(xmlUserStep)
                .end()
                .build();
    }

    @Bean
    Step xmlFileToDatabaseStep() {
        return stepBuilderFactory.get(ImportUsersConst.XML_TO_DATABASE_STEP)
                .<UserDTO, User>chunk(10)
                .reader(xmlToDatabaseUserReader)
                .processor(xmlToDatabaseProcessor)
                .writer(xmlToDatabaseUserWriter)
                .build();
    }

    // READER PROCESSOR WRITER
    @Bean
    ItemReader<UserDTO> xmlFileItemReader(@Value("${inputFile}") Resource inputFile) {
        //
        StaxEventItemReader<UserDTO> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(inputFile);
        xmlFileReader.setFragmentRootElementName("user");

        //
        Jaxb2Marshaller userMarshaller = new Jaxb2Marshaller();
        userMarshaller.setClassesToBeBound(UserDTO.class);

        xmlFileReader.setUnmarshaller(userMarshaller);
        return xmlFileReader;
    }


}

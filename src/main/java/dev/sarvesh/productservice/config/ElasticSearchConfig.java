//package dev.sarvesh.productservice.config;
//
//import dev.sarvesh.productservice.repositories.ProductOpenSearchRepository;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.opensearch.client.RestClientBuilder;
//import org.opensearch.spring.boot.autoconfigure.RestClientBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackageClasses = ProductOpenSearchRepository.class)
//@ComponentScan(basePackageClasses = ProductOpenSearchRepository.class)
//public class ElasticSearchConfig {
//
////    @Bean
////    RestClientBuilderCustomizer customizer() {
////       return new RestClientBuilderCustomizer() {
////           @Override
////           public void customize(RestClientBuilder builder) {
////               try {
////                   builder.setHttpClientConfigCallback(HttpAsyncClientBuilder.create().setSSLContext(SSLContextBuilder
////                           .create()
////                           .build()))
////               } catch (final KeyManagementException | NoSuchAlgorithmException | KeyStoreException ex) {
////                   throw new RuntimeException("Failed to initialize SSL Context instance", ex);
////               }
////           }
////       }
////    }
//
//}

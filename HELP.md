# Getting Started
Test application to show how glue schema registry can interrupt class path, and cause very default sqs clients
and other aws api clients to fail to instantiate without specifically specifying http client when glue schema registry
is upgraded 1.0.2 -> 1.1.0 as a result of https://github.com/awslabs/aws-glue-schema-registry/pull/49

It uses spring since it's generally easy to get "something" running in java - no fiddling with directly running java main.

This can be tested by running `maven spring-boot:run` and on schema-registry-common 1.0.2,
it successfully shows `SqsClient started successfully.`
while on 1.1.0,  it'll fail to boot with
`software.amazon.awssdk.core.exception.SdkClientException: Multiple HTTP implementations were found on the classpath.`


```
mvn spring-boot:run
```

Error message should look like:
```
 software.amazon.awssdk.core.exception.SdkClientException: Multiple HTTP implementations were found on the classpath. To avoid non-deterministic loading implementations, please explicitly provide an HTTP client via the client builders, set the software.amazon.awssdk.http.service.impl system property with the FQCN of the HTTP service to use as the default, or remove all but one HTTP implementation from the classpath
        at software.amazon.awssdk.core.exception.SdkClientException$BuilderImpl.build(SdkClientException.java:111) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.internal.http.loader.ClasspathSdkHttpServiceProvider.loadService(ClasspathSdkHttpServiceProvider.java:62) ~[sdk-core-2.20.16.jar:na]
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
        at java.base/java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
        at java.base/java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647) ~[na:na]
        at software.amazon.awssdk.core.internal.http.loader.SdkHttpServiceProviderChain.loadService(SdkHttpServiceProviderChain.java:44) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.internal.http.loader.CachingSdkHttpServiceProvider.loadService(CachingSdkHttpServiceProvider.java:46) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.internal.http.loader.DefaultSdkHttpClientBuilder.buildWithDefaults(DefaultSdkHttpClientBuilder.java:40) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.client.builder.SdkDefaultClientBuilder.lambda$resolveSyncHttpClient$7(SdkDefaultClientBuilder.java:355) ~[sdk-core-2.20.16.jar:na]
        at java.base/java.util.Optional.orElseGet(Optional.java:364) ~[na:na]
        at software.amazon.awssdk.core.client.builder.SdkDefaultClientBuilder.resolveSyncHttpClient(SdkDefaultClientBuilder.java:355) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.client.builder.SdkDefaultClientBuilder.finalizeSyncConfiguration(SdkDefaultClientBuilder.java:294) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.core.client.builder.SdkDefaultClientBuilder.syncClientConfiguration(SdkDefaultClientBuilder.java:184) ~[sdk-core-2.20.16.jar:na]
        at software.amazon.awssdk.services.sqs.DefaultSqsClientBuilder.buildClient(DefaultSqsClientBuilder.java:36) ~[sqs-2.20.16.jar:na]
        at software.amazon.awssdk.services.sqs.DefaultSqsClientBuilder.buildClient(DefaultSqsClientBuilder.java:25) ~[sqs-2.20.16.jar:na]
        at software.amazon.awssdk.core.client.builder.SdkDefaultClientBuilder.build(SdkDefaultClientBuilder.java:150) ~[sdk-core-2.20.16.jar:na]
        at com.example.demo.packag.TestComponent.<init>(TestComponent.java:16) ~[classes/:na]

```


Tested with (showing sdkman artifacts as well):

```
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: /Users/dbottini/.sdkman/candidates/maven/current
Java version: 17.0.7, vendor: Amazon.com Inc., runtime: /Users/dbottini/.sdkman/candidates/java/17.0.7-amzn
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "13.3.1", arch: "aarch64", family: "mac"
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)


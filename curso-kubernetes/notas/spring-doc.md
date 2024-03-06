## Spring-docs

#### 5. Implementaciones de Kubernetes PropertySource

El enfoque más común para configurar la aplicación de Spring Boot es crear un archivo o Un archivo OR que contiene pares clave-valor que proporcionan valores de personalización a su aplicación o arrancadores Spring Boot. Puede invalidar estas propiedades especificando las propiedades del sistema o el entorno Variables. ```application.properties application.yaml application-profile.properties application-profile.yaml```

Para habilitar esta funcionalidad, debe establecer las propiedades de configuración de la aplicación. Actualmente no se puede especificar un ConfigMap o un Secret para cargar usando , de forma predeterminada Spring Cloud Kubernetes cargará un ConfigMap y/o un Secret basado en la propiedad. Si no se establece, cargue un ConfigMap y/o Secret con el nombre. ```spring.config.import=kubernetes: spring.config.importspring.application.name spring.application.nameapplication```

Si desea cargar Kubernetes s durante la fase de arranque como funcionaba antes de la versión 3.0.x Puede agregar a la ruta de clases de la aplicación o establecerla como una variable de entorno.```PropertySource spring-cloud-starter-bootstrapspring.cloud.bootstrap.enabled=true```

Referencia: https://docs.spring.io/spring-cloud-kubernetes/reference/property-source-config.html
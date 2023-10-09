# Comandos de KUBECTL

#### Listar los deployments
```bash
kukectl get deployments
```
Puedes usar el abreviado que **deploy** en vez de **deployments**

#### Listar los pods

```bash
kubectl get pods
```
También funciona con la abreviación **pod** en vez de **pods**

##### Para inspeccionar un elemento de la salida del listado de los pods

```bash
  kubectl describe pod ${columna_name} 
```

##### Para mostrar los logs de un pod de la salida de listado de los pods
```bash
kubectl logs ${columna_name}
```

#### Para exponer en una IP
Existen distintos tipos:

- **--type=ClusterIP**
- **--type=NodePart** es para la comunicación externa
- **--type=LoadBalancer** para poder acceder a las varias replicas siempre y cuando lo soporte

```bash
  kubectl expose deployment ${name_pod} --port=${port} --type=ClusterIP
```

### Para listar todo

```bash
  kubectl get all
```

#### Para listar los servicios

```bash
kubectl get services
```

##### Para describir un servicio

```bash
kubectl describe service ${name_service}
```


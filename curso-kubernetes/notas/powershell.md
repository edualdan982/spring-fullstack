#### Para ejecutar varias veces un comando
```bash
Get-ChildItem -Filter 'svc-*' | Select-Object -ExpandProperty Name | ForEach-Object {kubectl apply -f $_}
```
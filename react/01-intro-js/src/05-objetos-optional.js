const invoce = {
  id: 10,
  name: "Compras de oficina",
  date: new Date(),
  client: {
    id: 2,
    name: "Jhon",
    lastNmae: "Doe",
    age: 20,
  },
  items: [
    { producto: "keyboard", price: 399, quantity: 2 },
    { producto: "mouse", price: 200, quantity: 1 },
    { producto: "paper", price: 100, quantity: 10 },
  ],
  total: function () {
    let total = 0;
    this.items.forEach( item => total += (item.price*item.quantity))
    return total;
  },
  greeting: () => {
    return `Hola ${invoce.client.name}`;
  },
};

console.log(invoce.company?.name)

if(invoce.company != undefined && invoce.company.name){
  console.lof('perfecto')
}else{
  console.log('no viene la empresa')
}


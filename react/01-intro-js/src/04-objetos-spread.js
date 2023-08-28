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

// invoce.total = 5000;
console.log(invoce.total());

const greeting = invoce.greeting();
console.log(greeting);

//Este es el operador spread donde se copea los datos
const invoce2 = {...invoce};

console.log(invoce2 === invoce)


import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import {useState} from "react";
import Products from "./shop/Products";
import BasketContext from "./shop/basket-context";
import Order from "./shop/Order";

function App() {

    const [savedBasket, setSavedBasket] = useState([]);

    function addToBasket(product) {
        let prevSavedBasket = savedBasket.slice()
        let wasInBasket = false
        for (const itemArr of prevSavedBasket) {
            if (product.ID === itemArr[0]){
                wasInBasket = true
                itemArr[2] += 1 // increase amount when in  basket
            }
        }

        if (!wasInBasket){
            prevSavedBasket.push([product.ID,product.name,1])
        }

        setSavedBasket(prevSavedBasket);
    }
    function removeFromBasket(productId) {
        let prevSavedBasket = savedBasket.slice()

        for (let i = 0; i < prevSavedBasket.length; i++) {
            if (productId === prevSavedBasket[i][0]){
                if (prevSavedBasket[i][2] > 1) {
                    prevSavedBasket[i][2] -= 1
                } else {
                    prevSavedBasket.splice(i,1)
                }
            }
        }
        setSavedBasket(prevSavedBasket);

    }
    function clearSavedBasket() {
        setSavedBasket([])
    }

    const basketContextValue = {
        basketList: savedBasket,
        removeProduct: removeFromBasket,
        addProduct: addToBasket,
        clearBasket: clearSavedBasket
    }




  return (
    <>
      <main >
        {/*<Shop/>*/}
          <BasketContext.Provider value = {basketContextValue}>
          <BrowserRouter>
              <nav  className="navbar navbar-expand-lg bg-dark">
                  <ul className="nav " >
                      <li id="nav-products" className="nav-item" ><Link to="/" className="nav-link text-light">Products</Link></li>

                      <li id="nav-orders" className="nav-item" ><Link className="nav-link text-light" to="/order">Order</Link></li>
                  </ul>
              </nav>

              <Routes >
                  <Route path="/" element={<Products className="col"/> }/>
                  <Route path="/order" element={<Order/>}/>

              </Routes>
          </BrowserRouter>


          </BasketContext.Provider>

      </main>
    </>
  );
}

export default App;

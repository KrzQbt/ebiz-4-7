import {useContext, useState} from "react";
import BasketContext from "./basket-context";
import axios from "axios";
import Basket from "./Basket";

function  Products() {

    const basketCtx = useContext(BasketContext)

    const [productsList,setProductsList] = useState([]);

    function getProductsInCategory(categoryIdNum){
        axios.get('http://127.0.0.1:1323/products')
            .then(res =>{
                setProductsList(res.data)

                // setCategories([{"id":1,"name":"cars"}]);

            })
    }

    getProductsInCategory()
    if (productsList.length == 0) {
        return (<div> no products available</div>)
    }

    return (<div className="row">
        <div className="col-9">

        {productsList.map( (product) => {

            let addOne = () => basketCtx.addProduct(product);
            let removeOne = () => basketCtx.removeProduct(product.ID);
             return (
                 <div>
                     <div className="card">
                             <div id="product-body" className="card-body">
                                 <h5 id="product-name"  className="card-title">{product.name}</h5>
                                 <p className="card-text">{product.description}</p>
                                 <button id="rmFromBasket" className="btn-sm btn-danger"  onClick={removeOne}>-</button>
                                 <button id="addToBasket" className="btn-sm btn-success" onClick={addOne}>+</button>

                             </div>
                     </div>

                 </div>
             )

            }

        )}</div>
        <div className="col">
        <Basket />
        </div>
            </div>)
}

export default Products;
import {useContext} from "react";
import BasketContext from "./basket-context";
import {Link} from "react-router-dom";

function Basket() {
    const basketCtx =  useContext(BasketContext)

    return (
        <div>
            <h4 id="basket-header">Basket</h4>
            <Link to="/order"><button id="but-orders" className="btn btn-primary">Order</button></Link>
            <ul className="list-group list-group-flush">
            {basketCtx.basketList.map((item,index) => {
                return <li className="list-group-item">
                    <h5 id="basket-product">{item[1]}</h5>
                    <h6 id="amount">Amount: {item[2]}</h6>
                </li>
            }) }
            </ul>
        </div>
    )

}

export default Basket
import {useContext, useState} from "react";
import BasketContext from "./basket-context";
import {Link} from "react-router-dom";
import axios from "axios";

function Order() {
    const basketCtx =  useContext(BasketContext)

    const [orderStatus, setOrderStatus] = useState("")

    const [statusMessage, setStatusMessage] = useState("")

    function order() {
        var orderObjects =[]

        for (const basketItem of basketCtx.basketList) {
            orderObjects.push({
                ID: basketItem[0],
                amount: basketItem[2]

            })
            // basketCtx. - usun caly basket - set, nowa meoda
        }

        axios.post('http://127.0.0.1:1323/order',orderObjects)
            .then(res => {
                setOrderStatus(res.status.toString())
                if (res.status === 201){
                    setStatusMessage("Order sucessful")
                } else {
                    setStatusMessage("Order failed")
                }
                // res.status
            })
        basketCtx.clearBasket()
    }






    if (!basketCtx.basketList.length == 0) {
        return (
            <div>
                <h2>Place your order</h2>
                {basketCtx.basketList.map((item, index) => {
                    let addOne = () => basketCtx.addProduct({ID: item[0], name: item[1], amount: item[2]});
                    let removeOne = () => basketCtx.removeProduct(item[0]);
                    return (<div>
                        <h5 id="basket-product">{item[1]}</h5>
                        <h6 id="amount">Amount: {item[2]}</h6>
                        <button id="decBasket" className="btn-sm btn-danger" onClick={removeOne}>-</button>
                        <button id="incBasket" className="btn-sm btn-success" onClick={addOne}>+</button>
                    </div>)
                })}

                <div className="mt-3"><button id="but-order" className="btn btn-primary " onClick={order}>Order</button></div>
            </div>
        )
    }
    return (<div>
        <p id="status-msg">{statusMessage}</p>
        <p id="empty-basket-msg">nothing in basket</p><li  className="nav-item" ><Link to="/" className="" id="comeback-link">Go back to products</Link></li></div>)

}

export default Order;
import {createContext} from "react";

const BasketContext = createContext({
    basketList: [],
    removeProduct: () => {},
    addProduct: () => {},
    clearBasket: () => {}
});

export default BasketContext;
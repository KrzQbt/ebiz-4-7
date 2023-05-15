package api

import (
	"github.com/labstack/echo/v4"
	"react-shop-go/service"
)

func AddRoutesToContext(e *echo.Echo) {
	e.GET("/products", service.GetAllProducts)
	e.POST("/order", service.OrderProducts)

}

package api

import "github.com/labstack/echo/v4"
import "go-shop/service"

// https://stackoverflow.com/questions/57595608/create-routing-modules-go-echo-restapi
func AddRoutesToContext(e *echo.Echo) {
	// Product routes
	e.GET("/product", service.GetAllProducts)
	e.GET("product/:id", service.GetProductById)
	e.POST("/product", service.CreateProduct)
	e.PUT("/product/:id", service.UpdateProductById)
	e.DELETE("/product/:id", service.DeleteProductById)

	// Category routes
	e.GET("/category", service.GetAllCategories)
	e.GET("/category/:id", service.GetCategoryById)
	e.POST("/category", service.CreateCategory)
	e.PUT("/category/:id", service.UpdateCategoryById)
	e.DELETE("/category/:id", service.DeleteCategoryById)

	// User + Basket routes
	e.POST("/user", service.CreateUser)

	//e.GET("//:id", service.GetCategoryById)
	//e.POST("/user", service.CreateCategory)
	//e.PUT("/category/:id", service.UpdateCategoryById)
	//e.DELETE("/basket/:id", service.DeleteCategoryById)

}

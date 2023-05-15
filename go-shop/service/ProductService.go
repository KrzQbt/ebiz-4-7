package service

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"go-shop/domain"
	"gorm.io/gorm"
	"net/http"
)

func GetAllProducts(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

func GetProductsInCategory(c echo.Context) error {
	//db, _ := c.Get("db").(*gorm.DB)
	
	return c.JSON(http.StatusOK, "")
}

func CreateProduct(c echo.Context) error {
	product := new(domain.Product)
	err := c.Bind(product)
	if err != nil {
		return err
	}
	fmt.Print(product)

	db, _ := c.Get("db").(*gorm.DB)
	db.Create(&product)

	return c.JSON(http.StatusCreated, "heh")
}

func GetProductById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

func UpdateProductById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}
func DeleteProductById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

//func getProductById(id int) {
//	print(id)
//}

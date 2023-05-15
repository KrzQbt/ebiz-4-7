package service

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"net/http"
	"react-shop-go/domain"
)

func GetAllProducts(c echo.Context) error {
	products := []domain.Product{}
	db, _ := c.Get("db").(*gorm.DB)
	db.Find(&products)
	return c.JSON(http.StatusOK, products)
}

func OrderProducts(c echo.Context) error {
	products := &[]domain.Order{}
	err := c.Bind(products)
	if err != nil {
		return err
	}
	fmt.Print("ordered: ", products)
	return c.JSON(http.StatusCreated, "heh")
}

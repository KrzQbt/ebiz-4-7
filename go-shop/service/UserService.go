package service

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"go-shop/domain"
	"gorm.io/gorm"
	"net/http"
)

func CreateUser(c echo.Context) error {

	user := new(domain.User)
	err := c.Bind(user)
	if err != nil {
		fmt.Print(err)
		return err
	}
	db, _ := c.Get("db").(*gorm.DB)
	db.Create(&user)

	basket := new(domain.Basket)
	basket.UserID = user.ID
	db.Create(basket)

	fmt.Print(user)
	return c.JSON(http.StatusCreated, "heh")
}
func Login(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

func AddAmountOfProductToBasket(c echo.Context) error {
	// user should store basketID on client, or it should be resolved here?
	// bind from login

	return c.JSON(http.StatusOK, "")
}

func RemoveProductFromBasket(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

func PayForBasket(c echo.Context) error {
	// clear

	return c.JSON(http.StatusOK, "")

}

func UpdateUserById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}
func DeleteUserById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

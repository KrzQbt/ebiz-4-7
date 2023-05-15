package service

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"go-shop/domain"
	"gorm.io/gorm"
	"net/http"
)

func GetAllCategories(c echo.Context) error {
	db, _ := c.Get("db").(*gorm.DB)

	var categories []domain.Category

	db.Find(&categories)

	return c.JSON(http.StatusOK, categories)
}

func CreateCategory(c echo.Context) error {
	category := new(domain.Category)

	err := c.Bind(category)
	if err != nil {
		fmt.Print(err)
		return err
	}

	db, _ := c.Get("db").(*gorm.DB)
	db.Create(category)

	return c.JSON(http.StatusCreated, "heh")
}

func GetCategoryById(c echo.Context) error {
	db, _ := c.Get("db").(*gorm.DB)
	var category = new(domain.Category)

	id := c.Param("id")
	fmt.Print(id)
	//strconv.Atoi(id) later to avoid weird params and inhections
	db.Find(category, id)

	return c.JSON(http.StatusOK, category)
}

func UpdateCategoryById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}
func DeleteCategoryById(c echo.Context) error {

	return c.JSON(http.StatusOK, "")
}

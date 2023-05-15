package main

import (
	"github.com/labstack/echo/v4"
	"go-shop/api"
	"go-shop/domain"
	"go-shop/service"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

func main() {
	dsn := "root@tcp(127.0.0.1:3306)/shop?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	// Migrate the schema
	db.AutoMigrate(
		domain.Product{},
		domain.Category{},
		domain.User{},
		domain.Basket{},
		domain.BasketItem{},
	)

	e := echo.New()
	e.Use(service.SetDBGormContext(db))
	api.AddRoutesToContext(e)

	e.Logger.Fatal(e.Start(":1323"))
}

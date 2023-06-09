package domain

import "gorm.io/gorm"

type Product struct {
	gorm.Model
	Name        string
	Description string
	CategoryID  uint `json:"category_id"`
	Category    Category
}

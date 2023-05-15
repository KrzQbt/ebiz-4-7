package domain

import "gorm.io/gorm"

type User struct {
	gorm.Model
	Login    string
	Password string // to hash later
}

# Delivery sample application

## Overview

JPA 학습 및 Kotlin 에 익숙해지기 위한 샘플 애플리케이션

## Environment

- Spring Boot 3.0.5
- Kotlin 1.7.x

## How to run

```bash
docker compose up -d
./gradlew :web:bootRun
```

## ERD

- Review
- Customer
- Shop
- **Order**
- Menu

```mermaid
---
title: delivery table
---
erDiagram
    CUSTOMER ||--|| CUSTOMER_ADDRESS: contains
    CUSTOMER ||--o{ ORDER: do
    CUSTOMER {
        Long id PK
        String name
        Address[] addresses
    }
    ORDER ||--|{ ORDER_MENU: contains
    ORDER {
        Long id PK
        Long customer_id FK
        Enum deliveryStatus
    }
    RESTAURANT ||--o{ REVIEW: contains
    CUSTOMER_ADDRESS {
        String city
        String street
        String zipcode
    }

    REVIEW {
        Long id PK
        Long restaurant_id FK
        Long order_id FK
        Long customer_id FK
        String contents
        Int rating
    }
    RESTAURANT ||--|| RESTAURANT_ADDRESS: contains
    RESTAURANT ||--|{ MENU: contains
    RESTAURANT {
        Long id PK
        String name
        Address address
    }
    RESTAURANT_ADDRESS {
        String city
        String street
        String zipcode
    }
    MENU {
        Long id PK
        Long restaurant_id FK
        String name
    }
    ORDER_MENU {
        Long id PK
        Long order_id FK
        Long menu_id FK
    }
    ORDER_MENU }|--|| MENU: contains
```

## Request

### Customer

- [x] 회원가입이 가능하다.
    - [x] 회원가입시에는 이름을 입력해야 한다.
    - [x] 회원가입시에는 주소를 입력해야 한다.
    - [x] 회원가입시에는 전화번호를 입력해야 한다.
- [ ] 주소를 변경할 수 있다.
- [ ] 여러 주소를 가질 수 있다. (중복 X)

### Order

- [x] 사용자는 주문을 생성할 수 있다.
    - [x] 한 번의 주문에 여러 메뉴를 담을 수 있다.
    - [x] 주문을 생성한 사용자는 **주문당 하나의 리뷰를 작성**할 수 있다.
    - [x] 사용자는 **한 번에 하나의 식당에만 주문**할 수 있다.
        - 주문 상태가 배달 완료 또는 취소여야 다시 주문을 생성할 수 있다.
- (Optional) 배달이 완료되기 전에는 다른 주문을 할 수 없다.

### Restaurant & Menu

- [x] 레스토랑을 등록할 수 있다.
    - [x] 메뉴와 함께 등록할 수 있다.

- pagination (optional)

### (Optional) Security

#### Owner

- 회원가입이 가능하다.
    - 회원가입시에는 이름을 입력해야 한다.
    - 회원가입시에는 가게명(상호)를 입력해야 한다.
    - 회원가입시에는 전화번호를 입력해야 한다.

#### Restaurant

- Owner 만 식당을 등록할 수 있다.

#### Menu

- 식당을 등록한 Owner 만 Menu 를 등록할 수 있다.

#### Rider

- 주문의 상태를 변경할 수 있다.

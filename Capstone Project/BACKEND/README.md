# Mini E‑Commerce (Minimal Security) — Angular + Spring Boot + MySQL

Implements the Capstone requirements using **minimal but sufficient security**:
- **JWT** issued by User Service (adds `role` and `userId` claims)
- **Gateway** with **Global CORS** only (no auth at gateway)
- **Product**: `GET /product/**` is public; `POST/PUT/DELETE` require ADMIN
- **Order/Cart**: require any authenticated user; **per-user guard** checks token `userId`
- **User**: registration & login open; others authenticated

Run order: Eureka → Gateway → User → Product → Order → Angular.

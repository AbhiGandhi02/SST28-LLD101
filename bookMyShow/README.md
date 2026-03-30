# Ticket Booking System 🎟️

An object-oriented software design for a scalable movie ticket booking system (similar to BookMyShow), focusing heavily on concurrency resolution and rule-based pricing.

## System Architecture

* **Concurrency Control (`SeatLockManager`):** The system resolves the critical race condition of multiple users attempting to book the exact same seats simultaneously. It utilizes `synchronized` blocks to atomically check seat availability and apply a temporary time-to-live (TTL) lock. If User B requests a seat that User A has temporarily locked for payment, User B's request throws an exception, while freeing the seat if User A's TTL expires.
* **Strategy Pattern (`PricingStrategy`):** Implements dynamic pricing completely decoupled from the main booking logic. It can easily inject different rules (e.g., weekend surges, occupancy-based demand multipliers) at runtime without altering core classes (Open/Closed Principle).
* **Entities:** A strict hierarchical layout (`Theater` -> `Screen` -> `Show` -> `ShowSeat` -> `Seat`) ensures data normalization and scalability.

## How to Run

Compile and execute all classes using standard Java commands in the root directory:

```bash
javac *.java
java Main
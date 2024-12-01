package model;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER,
    ROLE_CLUB_SUPER,           // Super user with elevated privileges
    ROLE_CLUB_ADMIN,
    ROLE_CLUB_MANAGER,    // Moderate access, can manage events and bookings
    ROLE_CLUB_MEMBER,     // Standard access, can view events and book facilities
    ROLE_CLUB_RENTER,
    ROLE_CLUB_GUEST,      // Limited access, can view public events only
    ROLE_CLUB_EVENT_ORGANIZER, // Can manage own events and track RSVPs
    ROLE_CLUB_VENDOR_SUPPLIER, // Can view assigned orders and update status
    ROLE_CLUB_SUPPORT_USER     // Access to member queries and support tools
}
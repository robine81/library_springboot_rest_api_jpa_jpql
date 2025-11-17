
User:
@OneToMany User -> Loans

Book:
@OneToMany Book -> Loans

Loan (join entity between User and Book - loanDate, returnDate):
@ManyToOne Loans -> User
@ManyToOne Loans - Book




import random

choices = ['Scissors', 'Rock', 'Paper']
player_score = 0
computer_score = 0
player_review = {}
computer_review = {}
count_winner = {}

print("\nWELCOME TO THE ROCK, PAPER AND SCISSORS GAME BY byteKeith+_+!\n")
while True:
    name = input("Enter the name you'll use for the game: ").capitalize()
    if len(name) >= 4:
        while True:
            repeated = input("Please repeat that: ").capitalize()
            if repeated == name:
                print(f"\nWelcome {name}..\n")
                break
            print("Mismatch!")
            continue
        break
    print("Length of your name is too short, 4 characters is the minimum!\n")

print("** The game has 10 counts, Player who accumulates more points wins! **")
print("Lets start!\n")


def computer_choice():
    for t in range(2):
        for i in range(4):
            random.shuffle(choices)
    return random.choice(choices)

def player_choice(param1):
    if param1 == 1:
        print("You play Rock.")
        player_review.update({l: "Rock"})
    elif param1 == 2:
        print("You play Paper.")
        player_review.update({l: "Paper"})
    else:
        print("You play Scissors.")
        player_review.update({l: "Scissors"})

l = 1
while l < 11:
    try:
        print('\033[4m' +
              f"{name} = {player_score} || COMPUTER = {computer_score}" + '\033[0m')
        if l == 10:
            print("This is the final count, take note..")
        else:
            print(f"{11 - l} COUNTS TO GO..")

        user = int(input(
            "\nEnter your choice (1 for Rock, 2 for Paper or 3 for Scissors): "))
        if user in range(1, 4):
            player_choice(user)
            computer = computer_choice()
            print(f"Computer plays {computer}.")
            computer_review.update({l: computer})
            if (user == 1 and computer == "Scissors") or (user == 2 and computer == "Rock") or (
                    user == 3 and computer == "Paper"):
                print(f"{name} + 1.\n")
                player_score += 1
                count_winner.update({l: name})
            elif (computer == "Rock" and user == 3) or (computer == "Paper" and user == 1) or (
                    computer == "Scissors" and user == 2):
                print("Computer + 1.\n")
                computer_score += 1
                count_winner.update({l: "Computer"})
            else:
                print("Same choices.\n")
                count_winner.update({l: "Same choices, no one won the count."})
        else:
            print("Please enter 1, 2, 3 or 4.\n")
            continue
        l += 1
    except ValueError:
        print("Please check your input.\n")

if player_score > computer_score:
    print(f"Congratulations {name}, you're the winner!!")
    print(
        '\033[1m' + f"Final score ->  You = {player_score}   Computer = {computer_score}\n" + '\033[0m')
elif computer_score > player_score:
    print(f'Game over {name}, you lost to the Computer by {computer_score - player_score} point(s)!!')
    print(
        '\033[1m' + f"Final score ->  You = {player_score}   Computer = {computer_score}\n" + '\033[0m')
else:
    print("That was a tie!!")
    print(
        '\033[1m' + f"Final score ->  You = {player_score}   Computer = {computer_score}\n" + '\033[0m')

while True:
    def player_review_func():
        print(f"\nFor {name}...")
        print("Count    Choice")
        for j, k in player_review.items():
            print(f"  {j} {" -> "} {k}")
    def computer_review_func():
        print("\nFor the Computer...")
        print("Count    Choice")
        for m, n in computer_review.items():
            print(f"  {m} {" -> "} {n}")
    def count_winner_review_func():
        print("\nWinner of each count...")
        print("Count    Winner")
        for y, z in count_winner.items():
            print(f"  {y} {" -> "} {z}")

    choice = input("Do you want me to show you the participants' choices review? ('Y' for Yes or 'N' for No): ")
    if choice.lower() == 'y':
        print("Ok, here is it..")
        player_review_func()
        computer_review_func()
        count_winner_review_func()
        break
    elif choice.lower() == 'n':
        print("\nOk, Thank you very much for playing the game by byteKeith+_+!")
        break
    else:
        print("Please enter Y or N.")


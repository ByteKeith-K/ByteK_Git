import random

print("\nYou have only 5 chances in the guess game...\n")
choose = ['a', 'x', '6', 'k']
l = 0
count = 0

while l < 5:
    try:
        choice = int(input(
            "Enter the position you think 'a' is found, (1, 2, 3 or 4): "))
        if choice in range(1, 5):
            for t in range(4):
                for i in range(2):
                    random.shuffle(choose)
            print(choose)
            var = choose[choice - 1]
            if var == 'a':
                print('Correct.\n')
                count += 1
            else:
                print(
                    f"Oops not {choice}, correct position is {choose.index('a') + 1}.\n")
        else:
            print('Follow instructions.\n')
            continue
    except ValueError:
        print('That is not a number, no strings allowed.\n')
        continue
    l += 1
    attempts_left = 5 - l
    print('\033[4m' +
          f'You have {attempts_left} attempt(s) left.\n' + '\033[0m')
    if attempts_left == 0:
        print(f"Thank you for using my program, you got {count} point(s).")
        print('Exiting now, bye....\n')

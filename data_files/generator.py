# -*- coding: utf-8 -*-
"""
Created on Tue Apr 24 04:52:30 2018

@author: Arsanuos
"""

import random

def write_to_file(n):
    with open('book.csv', 'w') as file:
        for i in range(n):
            file.write("{0},thebook,1999,romance,someone{0}\n".format(i))


    with open('publisher.csv', 'w') as file:
        file.write('someone,101alex,01245432\n'.format(i))
        for i in range(n):
            file.write('someone{0},101alex,01245432\n'.format(i))

    with open('author.csv', 'w') as file:
        for i in range(n):
            file.write("author{0},{0}\n".format(i))

    with open('orders.csv', 'w') as file:
        for i in range(n):
            file.write("{0},{1}\n".format(i * random.randint(1, 4), i))

    with open('quantity_table.csv', 'w') as file:
        for i in range(n):
            file.write("{0},{1},{2},{3}\n".format(i, i * random.randint(1, 10), i * random.randint(1, 4), i * random.uniform(1, 3) * 1.5))

    with open('log.csv', 'w') as file:
        for i in range(n):
            file.write("a{0}@b.com,{0},{1},\"2018-12-01\"\n".format(i, i * random.randint(1, 4)))

    with open('cart.csv', 'w') as file:
        for i in range(n):
            file.write("a{0}@b.com,{0},{1}\n".format(i,i * random.randint(1, 4)))

    with open('user.csv', 'w') as file:
        for i in range(n):
            file.write("deif{0},a{0}@b.com,123456,deif,elmandoob,01234567{0},101alex,{1}\n".format(i, random.randint(0,1)))

    print("Done!")
write_to_file(1000000)

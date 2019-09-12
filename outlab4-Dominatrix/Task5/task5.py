import sys
import pandas as pd
import matplotlib.pyplot as plt
import argparse

parser = argparse.ArgumentParser(description='This program plots graphs fro,m the csv file')
parser.add_argument('--data', required=True, help='the path of data file')
file = vars(parser.parse_args(sys.argv[1:]))['data']

algorithms = ['round-robin', 'epsilon-greedy', 'ucb', 'kl-ucb', 'thompson-sampling']
epsilons = [0.002, 0.02, 0.2]

df = pd.read_csv(file).groupby('instance')
instcnt = 3
for instance, inst in df:
    alggrp = inst.groupby('algorithm')
    for algo in algorithms:
        alg = alggrp.get_group(algo)
        if algo != 'epsilon-greedy':
            means = alg.groupby('horizon').mean()
            reg = pd.Series(means['REG']).to_numpy()
            hor = means.index.tolist()
            plt.plot(hor, reg, label=algo)
        else:
            for epsilon in epsilons:
                eps = alg.groupby('epsilon').get_group(epsilon).groupby('horizon').mean()
                reg = pd.Series(eps['REG']).to_numpy()
                hor = eps.index.tolist()
                plt.plot(hor, reg, label='epsilon-greedy with epsilon=' + str(epsilon))
    plt.xscale('log')
    plt.yscale('log')
    plt.xlabel('horizon')
    plt.ylabel('Regret')
    plt.title('Instance ' + str(instcnt) + ' - both axes in log scale')
    plt.legend()
    plt.savefig('instance' + str(instcnt) + '.png')
    plt.close()
    instcnt -= 1

import random
import matplotlib.pyplot as plt

#used to cleanup floats
def truncate(num, n):
    integer = int(num * (10**n))/(10**n)
    return float(integer)

# Sim init conditions
t_max = 1000            # Longest number of steps to run
I_0 = 0.01              # Proportion of initially sick (seed)
S_0 = 1-I_0
R_0 = 0
D_0 = 0

#init parameters, feel free to modulate
num_tests = 10          # number of tests to run
inf_cutoff = 100        # Cutoff for infected population, used for speed, stops when infected < than this, 100 is a good number
pop_size = 50000        # Pop size, 50000 is a nice number
recov_mean = 25         # Mean recovery time, in steps
recov_dev = 10           # Dev from mean, a uniform distribution
p_trans = 0.45          # Rate of transmission, should be > 0
mort = 0.05             # Mortality rate, between 0 and 1

single_plot = True      # Determine if only a single plot should be generated

# Modifiers added to their respective vars after each run of the sim, 0 indicates it stays constant
pop_size_mod = 0
recov_mean_mod = 0
recov_dev_mod = 0
p_trans_mod = 0.15
mort_mod = 0.05

for count in range(0, num_tests):
    # init counters
    pop = []
    t = 1
    infected = int(I_0 * pop_size) # Properly seedds the infected
    sus = pop_size-infected # Sets susceptible pop
    recovered = 0
    dead = 0

    #init the list and seed init infected
    # 0 -> sus, 1 -> inf, 2 -> rec, 3 -> dead
    # Second thing in the list is for days to recovery
    for i in range(0, pop_size):
        pop.append([0,0])
    for i in range(0, infected):
        pop[i][0] = 1
        r = random.random()
        pop[i][1] = int(recov_mean + (recov_dev*(r-0.5)))

    # init data lists
    x1 = [0.0]
    s_d = [float(sus/pop_size)]
    i_d = [float(infected/pop_size)]
    r_d = [float(recovered/pop_size)]
    d_d = [float(dead/pop_size)]

    # Stop the sim on "end"
    while(t < t_max and infected > inf_cutoff):
        # q is just a dummy counter to track the number of entries that need to be checked
        # The first 'q' entries in pop are guranteed to be non-zero, and thus represents the non-suceptible population
        q = pop_size - sus
        for i in range(0, q):
            # this is to ignore recovered or dead individuals
            if(pop[i][0] == 1):
                # First roll to determine if a new person is infected
                # That p_trans * float(sus/pop_size) comes from the expression that S' = -t * I * S, and so is reflected here
                # The affect is that the infected curve is "smoother" and more realistic
                # (try removing it and you'll see what I mean)
                if (random.random() < p_trans*(float(sus/pop_size))) and (sus > 0):
                    # update next 0 tuple and update counters
                    pop[pop_size-sus-1][0] = 1
                    pop[pop_size-sus-1][1] = int(recov_mean + recov_dev*(random.random()-0.5))
                    infected+=1
                    sus-=1
                # determine if this individual recovers and/or decrement the recovery time counter
                if pop[i][1] <= 0:
                    pop[i][0] = 2
                    pop[i][1] = 0
                    recovered += 1
                    infected -= 1
                else:
                    pop[i][1] -= 1
                # determine if this individual dies
                if random.random() < mort:
                    pop[i][0] = 3
                    pop[i][1] = 0
                    dead += 1
                    infected -= 1
        # append aggregate data at end of each step
        x1.append(t)
        s_d.append(float(sus/pop_size))
        i_d.append(float(infected/pop_size))
        r_d.append(float(recovered/pop_size))
        d_d.append(float(dead/pop_size))
        t+=1

    #plot data at end of each sim
    plt.plot(x1, s_d, label = "Susceptible", color="blue")
    plt.plot(x1, i_d, label = "Infected", color="red")
    plt.plot(x1, r_d, label = "Recovered", color="green")
    plt.plot(x1, d_d, label = "Dead", color="black")
    if not single_plot:
        plt.title(label=("pop = " +str(pop_size) + ", trans = " + str(p_trans) + ", rec time = "+str(recov_mean)+"+/-"+str(recov_dev) +", mort = "+str(mort)))
    #    plt.set_ylabel('% of Pop.')
    #    plt.set_xlabel('Steps')
        plt.legend()
        plt.savefig("result_"+str(count+1)+".png")
        plt.clf()
    else:
        plt.savefig("cumulative_result.png")
    #modify params for consecutive tests
    p_trans = truncate(p_trans + p_trans_mod, 2)
    recov_mean = truncate(recov_mean + recov_mean_mod, 2)
    recov_dev = truncate(recov_dev + recov_dev_mod, 2)
    pop_size += pop_size_mod
    mort = truncate(mort + mort_mod, 2)

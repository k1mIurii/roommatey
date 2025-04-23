import csv,os
from collections import defaultdict

script_dir = os.path.dirname(os.path.abspath(__file__))
csv_path = os.path.join(script_dir, "locations.csv")

zip_counts = defaultdict(int)
duplicates = []


with open(csv_path, newline="",encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        zip_code = row['zip']
        zip_counts[zip_code] += 1
        if zip_counts[zip_code] == 2:
            duplicates.append(zip_code)


print(f"Duplicate zip codes: {len(duplicates)}")
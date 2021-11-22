echo "Staring Vault..."
docker-entrypoint.sh server -dev &
echo "Sleeping 10..."
sleep 10
echo "Vault Started."
echo "Authenticate into Vault"
vault login op4BumHD66eeu
echo "Adding secrets to Vault..."
vault kv put secret/order-service spring.datasource.username=root spring.datasource.password=root spring.datasource.url=jdbc:mysql://localhost:3306/db
vault kv put secret/product-service spring.datasource.username=root spring.datasource.password=root spring.datasource.url=jdbc:mysql://localhost:3306/db
vault kv put secret/inventory-service spring.datasource.username=root spring.datasource.password=root spring.datasource.url=jdbc:mysql://localhost:3306/db
while true
do
    sleep 1000
done
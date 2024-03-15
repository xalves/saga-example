$uri = "http://localhost:8080/order"

$order = @{
    "paymentDetails" = "bestCard"
}

$orderJson = $order | ConvertTo-Json

$headers = @{
    "Content-Type" = "application/json"
}

$response = Invoke-RestMethod -Method Post -Uri $uri -Body $orderJson -Headers $headers

Write-Output $response
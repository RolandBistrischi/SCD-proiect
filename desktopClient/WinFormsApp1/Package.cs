using System.Text.Json.Serialization;

namespace WinFormsApp1
{
    internal class Package
    {
        public int Id
        {
            get; set;
        }

        [JsonPropertyName("deliveryAddress")]
        public string DeliveryAddress
        {
            get; set;
        }

        [JsonPropertyName("created_on")]
        public DateTime CreatedOn
        {
            get; set;
        }

        [JsonPropertyName("status")]
        public PackageStatus Status
        {
            get; set;
        }

        [JsonPropertyName("courier")]
        public Courier? Courier
        {
            get; set;
        }
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    internal enum PackageStatus
    {
        NEW,
        PENDING,
        DELIVERED
    }
}

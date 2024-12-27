using System.Linq;
using System.Net.Http.Headers;
using System.Text.Json;

namespace WinFormsApp1
{
    internal class PackageService
    {
        private static readonly HttpClient client = new();

        public void createConnection()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:8083/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public static List<Package> GetPackages()
        {
            HttpResponseMessage response;

            try
            {
                response = client.GetAsync("package").Result;
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Server indisponibil! Eroare: {ex.Message}");
                return new();
            }

            if (!response.IsSuccessStatusCode)
            {
                MessageBox.Show($"Eroare: {response.StatusCode} - {response.ReasonPhrase}");
                return new();
            }

            try
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);

                return JsonSerializer.Deserialize<List<Package>>(resultString) ?? new();
            }
            catch (JsonException ex)
            {
                Console.WriteLine($"Deserialization failed: {ex.Message}");
                return new();
            }
        }

        public List<Courier?> GetBusyCouriers()
        {
            List<Package> packages = GetPackages() ?? new();
            return packages.Where(p => p.Courier != null && !string.IsNullOrEmpty(p.Courier.Name))
                .Select(p => p.Courier)
                .DistinctBy(c => c.Name)
                .ToList();
        }
    }
}

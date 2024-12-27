using System;
using System.Collections.Generic;
using System.Diagnostics.Metrics;
using System.Linq;
using System.Net;
using System.Net.Http.Headers;
using System.Net.Mail;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace WinFormsApp1
{
    internal class CourierService
    {
        private static readonly HttpClient client = new();


        public CourierService()
        {
            client.BaseAddress = new Uri("http://localhost:8083/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public List<Courier>? GetCouriers()
        {
            HttpResponseMessage response = client.GetAsync("courier").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                List<Courier>? courier = JsonSerializer.Deserialize<List<Courier>>(resultString);
                return courier;

            }
            return null;
        }

        public List<Courier> GetAllCouriersWithoutPendingPackages()
        {
            try
            {
                HttpResponseMessage response = client.GetAsync("courier/withoutPendingPackages").Result;
                if (response.IsSuccessStatusCode)
                {
                    string resultString = response.Content.ReadAsStringAsync().Result;
                    Console.WriteLine("received: " + resultString);
                    return JsonSerializer.Deserialize<List<Courier>>(resultString) ?? new List<Courier>();
                }
                return new List<Courier>();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Eroare: {ex.Message}");
                return new List<Courier>();
            }
        }

        public List<ManagerWithDeliveredCount> GetAllManagersAndTheirNumberOfPackages()
        {
            try
            {
                HttpResponseMessage response = client.GetAsync("courier/managersAndDeliveredNumber").Result;
                if (response.IsSuccessStatusCode)
                {
                    string resultString = response.Content.ReadAsStringAsync().Result;
                    Console.WriteLine("received: " + resultString);
                    var a = JsonSerializer.Deserialize<List<ManagerWithDeliveredCount>>(resultString) ?? new List<ManagerWithDeliveredCount>();
                    return a;
                }
                return new List<ManagerWithDeliveredCount>();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Eroare: {ex.Message}");
                return new List<ManagerWithDeliveredCount>();
            }
        }

        public void SendEmailToSelectedCouriers( EmailRequest emailRequest )
        {
            try
            {
                var content = new StringContent(JsonSerializer.Serialize(emailRequest));
                content.Headers.ContentType = new MediaTypeHeaderValue("application/json");

                HttpResponseMessage response = client.PostAsync("courier/sendEmail", content).Result;
                if (response.IsSuccessStatusCode)
                {
                    MessageBox.Show("Email-urile au fost trimise cu succes!");
                }
                else
                {
                    MessageBox.Show($"Eroare la trimiterea email-urilor: {response.ReasonPhrase}");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Eroare: {ex.Message}");
            }
        }


        public void SendEmailwithCSharp( List<Courier> couriersList )
        {
            try
            {
                MailMessage message = new();
                SmtpClient smtp = new();

                message.From = new MailAddress("bistrischiandrei1@gmail.com");
                message.To.Add(new MailAddress("hd7wngw538@gmail.com"));
                message.Subject = "Test";
                message.Body = "Content";

                smtp.Port = 8083;
                smtp.Host = "smtp.gmail.com";
                smtp.EnableSsl = true;
                smtp.UseDefaultCredentials = false;
                smtp.Credentials = new NetworkCredential("bistrischiandrei1@gmail.com", "ui76c4h1");
                smtp.DeliveryMethod = SmtpDeliveryMethod.Network;
                smtp.Send(message);
            }
            catch (Exception ex)
            {
                MessageBox.Show("err: " + ex.Message);
            }
        }


    }

}

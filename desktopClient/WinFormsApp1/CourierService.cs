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

        /*public void createConnection()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:8083/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }*/

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

        public List<Courier> GetAllCourierwitoutPendingPackages() {
            List<Courier>? courier = null;
            HttpResponseMessage response = client.GetAsync("courier/withoutPendingPackages").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                 courier = JsonSerializer.Deserialize<List<Courier>>(resultString);
                           }

            return courier;
        }

      public List<Courier> GetAllManagersAndTheirNumberOfPackages() {
            List<Courier>? courier = null;
            HttpResponseMessage response = client.GetAsync("courier/managersAndDeliveredNumber").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                courier = JsonSerializer.Deserialize<List<Courier>>(resultString);
            }

            return courier;
        }

        public void SendEmailtoAllSelectedPersons() {
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
